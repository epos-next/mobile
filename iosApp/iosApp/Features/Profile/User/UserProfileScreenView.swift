//
//  UserProfileScreenView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct UserProfileScreenView: View {
    @EnvironmentObject var userObservable: UserObservable
    
    @State private var name: String = ""
    @State private var username: String = ""
    @State private var dateOfBirth: Date = Date()
    @State private var state: ButtonState = .idle
    
    private func handleSubmit() {
        if state == .loading { return }
        
        withAnimation(.spring()) {
            state = .loading
        }
        
        userObservable.reducer.update(
            name: name,
            username: username,
            dateOfBirth: dateToKtx(dateOfBirth),
            completionHandler: { _, err in
                withAnimation(.spring()) {
                    state = .done
                }
                Timer.scheduledTimer(withTimeInterval: 1.0, repeats: false) { timer in
                    withAnimation(.spring()) {
                        state = .idle
                    }
                }
            }
        )
    }
    
    var body: some View {
        ScrollView {
            VStack(spacing: 30) {
                ProfileHeaderView(
                    text: "Профиль",
                    color: Color(hex: 0xFF68D676),
                    icon: "user_icon_96"
                )
                
                VStack(spacing: 20) {
                    FilledInputView(text: $name, placeholder: "Введите ваше имя", label: "Имя")
                    FilledInputView(text: $username, placeholder: "Введите ваш username", label: "Username")
                    DateInputView(date: $dateOfBirth, placeholder: "Дата рождения")
                    MainButton("Сохранить", action: { handleSubmit() }, state: state)
                }.padding(.horizontal, 20)
                
            }
        }.navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
        .ignoresSafeArea()
        .onAppear {
            if let name = (userObservable.state as? UserState.Authorized)?.user.name {
                self.name = name
            }
            
            if let username = (userObservable.state as? UserState.Authorized)?.user.username {
                self.username = username
            }
            
            if let dateOfBirth = (userObservable.state as? UserState.Authorized)?.user.dateOfBirth {
                self.dateOfBirth = KtxToDate(dateOfBirth)
            }
        }
    }
}

struct UserProfileScreenView_Previews: PreviewProvider {
    static var previews: some View {
        UserProfileScreenView()
    }
}
