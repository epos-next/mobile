//
//  AppNameView.swift
//  iosApp
//
//  Created by Ярослав Зотов on 19.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct LoginAppNameView: View {
    var body: some View {
        Text("Эпос Next")
            .font(.custom("AvenirNext-Medium", size: 24))
            .fontWeight(.semibold)
            .foregroundColor(.contrast)
    }
}

struct LoginAppNameView_Previews: PreviewProvider {
    static var previews: some View {
        LoginAppNameView()
    }
}
