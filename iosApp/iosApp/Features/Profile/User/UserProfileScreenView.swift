//
//  UserProfileScreenView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct UserProfileScreenView: View {
    var body: some View {
        ScrollView {
            ProfileHeaderView(
                text: "Профиль",
                color: Color(hex: 0xFF68D676),
                icon: "user_icon_96"
            )
        }.navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
        .ignoresSafeArea()
    }
}

struct UserProfileScreenView_Previews: PreviewProvider {
    static var previews: some View {
        UserProfileScreenView()
    }
}
