//
//  ProfileScreenView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProfileScreenView: View {
    
    @EnvironmentObject var userObservable: UserObservable
    @EnvironmentObject var darkModeObservable: DarkModeObservable
    
    @State private var isDark = false
    
    @ViewBuilder
    var body: some View {
        let state = userObservable.state
        
        NavigationView {
            ScrollView {
                VStack(spacing: 20) {
                    UserAvatarView()
                        .padding(.top, 50)
                    
                    if let name = (state as? UserState.Authorized)?.user.name {
                        UserNameView(name)
                    }
                    
                    MenuTileView(
                        text: "Профиль",
                        icon: "user_icon",
                        color: Color(hex: 0xFF68D676),
                        route: { UserProfileScreenView() }
                    )
                    
                    
                    MenuTileView(
                        text: "О разработчиках",
                        icon: "dev_icon",
                        color: Color(hex: 0xFF83D4FC),
                        route: { Text("test") }
                    )
                    
                    LogoutButton()
                }
            }
            .onAppear {
                print(darkModeObservable.state)
                isDark = darkModeObservable.state
            }
            .navigationBarHidden(true)
        }
    }
}


private struct LogoutButton: View {
    @EnvironmentObject var userObservable: UserObservable
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Button(action: { userObservable.reducer.logout { _, __ in } }) {
            HStack(spacing: 15) {
                Image("exit_icon")
                    .resizable()
                    .padding(4)
                    .foregroundColor(colorScheme == .light ? Color.white : Color(hex: 0xFFF18477))
                    .background(RoundedRectangle(cornerRadius: 5).fill(colorScheme == .light ? Color(hex: 0xFFF18477) : Color.white.opacity(0.04)))
                    .frame(width: 32, height: 32)
                
                Text("Выйти")
                    .font(.custom("AvenirNext-Regular", size: 16))
                    .foregroundColor(Color.textPrimary)
                
                Spacer()
            }.padding(.horizontal, 20)
        }.buttonStyle(PlainButtonStyle())
    }
}

struct ProfileScreenView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileScreenView()
    }
}
