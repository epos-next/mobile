//
//  HomeTitleView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HomeTitleView: View {
    var text: String
    
    var body: some View {
        Text(text)
            .font(.custom("AvenirNext-Medium", size: 15))
            .foregroundColor(.textPrimary)
    }
}

struct HomeTitleView_Previews: PreviewProvider {
    static var previews: some View {
        HomeTitleView(text: "Some text there")
    }
}
