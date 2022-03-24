//
//  UserAvatarView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct UserAvatarView: View {
    var body: some View {
        Image("user_placeholder")
            .resizable()
            .frame(width: 100, height: 100)
    }
}

struct UserAvatarView_Previews: PreviewProvider {
    static var previews: some View {
        UserAvatarView()
    }
}
