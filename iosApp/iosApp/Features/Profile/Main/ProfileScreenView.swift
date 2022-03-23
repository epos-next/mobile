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
                    
                    HStack(spacing: 15) {
                        Image("moon_icon")
                            .resizable()
                            .padding(4)
                            .background(RoundedRectangle(cornerRadius: 5).fill(Color(hex: 0xFF4957CD)))
                            .frame(width: 32, height: 32)
                        
                        Text("Темная тема")
                            .font(.custom("AvenirNext-Regular", size: 16))
                            .foregroundColor(Color.textPrimary)
                        
                        Spacer()
                        
                        Toggle("", isOn: $isDark)
                            .labelsHidden()
                            .contentShape(Rectangle())
                            .frame(width: 30, height: 10)
                    }.padding(.horizontal, 20)
                    
                    MenuTileView(
                        text: "О разработчиках",
                        icon: "dev_icon",
                        color: Color(hex: 0xFF83D4FC),
                        route: { Text("test") }
                    )
                    
                    Button(action: {}) {
                        HStack(spacing: 15) {
                            Image("exit_icon")
                                .resizable()
                                .padding(4)
                                .background(RoundedRectangle(cornerRadius: 5).fill(Color(hex: 0xFFF18477)))
                                .frame(width: 32, height: 32)
                            
                            Text("Выйти")
                                .font(.custom("AvenirNext-Regular", size: 16))
                                .foregroundColor(Color.textPrimary)
                            
                            Spacer()
                        }.padding(.horizontal, 20)
                    }.buttonStyle(PlainButtonStyle())
                }
            }
            .navigationBarHidden(true)
        }
    }
}

struct ProfileScreenView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileScreenView()
    }
}
