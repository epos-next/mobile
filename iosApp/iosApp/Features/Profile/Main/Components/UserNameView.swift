//
//  UserNameView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct UserNameView: View {
    var text: String
    
    init(_ text: String) {
        self.text = text
    }
    
    var body: some View {
        Text(text)
            .font(.custom("AvenirNext-Regular", size: 24))
            .foregroundColor(Color.textPrimary)
    }
}

struct UserNameView_Previews: PreviewProvider {
    static var previews: some View {
        UserNameView("Ярослав Зотов")
    }
}
