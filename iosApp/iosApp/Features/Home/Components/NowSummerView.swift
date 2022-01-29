//
//  NowSummerView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct NowSummerView: View {
    var body: some View {
        Text("Сейчас лето, иди отдыхай, дурачек)")
            .font(.custom("AvenirNext-Regular", size: 14))
            .frame(minWidth: 0, maxWidth: .infinity, alignment: .center)
            .padding(.vertical, 50)
    }
}

struct NowSummerView_Previews: PreviewProvider {
    static var previews: some View {
        NowSummerView()
    }
}
