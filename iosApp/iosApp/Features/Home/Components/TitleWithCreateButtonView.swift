//
//  TitleWithCreateButtonView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct TitleWithCreateButtonView: View {
    var text: String
    var onTap: () -> Void
    
    var body: some View {
        HStack {
            HomeTitleView(text: text)
            Spacer()
            Button(action: { onTap() }) {
                Image("add_icon")
                    .resizable()
                    .frame(width: 18, height: 18)
                    
            }
        }
    }
}

struct TitleWithCreateButtonView_Previews: PreviewProvider {
    static var previews: some View {
        TitleWithCreateButtonView(text: "Some text there", onTap: {})
    }
}
