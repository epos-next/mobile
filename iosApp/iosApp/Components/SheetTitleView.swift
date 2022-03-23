//
//  SheetTitleView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SheetTitleView: View {
    let text: String
    
    init (_ text: String) {
        self.text = text
    }
    
    var body: some View {
        Text(text)
            .font(.custom("AvenirNext-Medium", size: 16))
            .multilineTextAlignment(.center)
            .foregroundColor(Color.textPrimary)
    }
}

struct SheetTitleView_Previews: PreviewProvider {
    static var previews: some View {
        SheetTitleView("Новая контрольная работа")
    }
}
