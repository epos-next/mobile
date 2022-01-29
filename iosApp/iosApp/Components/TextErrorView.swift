//
//  TextError.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct TextErrorView: View {
    var error: String
    
    var body: some View {
        Text(error)
            .font(.custom("AvenirNext-Regular", size: 15))
            .frame(minWidth: 0, maxWidth: .infinity)
            .padding(.vertical, 15)
            .background(Color.lightError)
            .foregroundColor(.error)
    }
}

struct TextErrorView_Previews: PreviewProvider {
    static var previews: some View {
        TextErrorView(error: "Some error happened!")
    }
}
