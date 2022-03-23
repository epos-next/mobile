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
                    
                    MenuTileView<EmptyView>(
                        text: "Профиль",
                        icon: "user_icon",
                        color: Color(hex: 0xFF68D676),
                        onTap: {  }
                    )
                    
                    MenuTileView(
                        text: "Темная тема",
                        icon: "moon_icon",
                        color: Color(hex: 0xFF4957CD),
                        onTap: {  },
                        suffix: {
                            Toggle("", isOn: $isDark)
                                .labelsHidden()
                                .contentShape(Rectangle())
                                .frame(width: 30, height: 10)
                                
                        }
                    )
                    
                    MenuTileView<EmptyView>(
                        text: "О разработчиках",
                        icon: "dev_icon",
                        color: Color(hex: 0xFF83D4FC),
                        onTap: {  }
                    )
                    
                    MenuTileView<EmptyView>(
                        text: "Выйти",
                        icon: "exit_icon",
                        color: Color(hex: 0xFFF18477),
                        onTap: {  }
                    )
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
