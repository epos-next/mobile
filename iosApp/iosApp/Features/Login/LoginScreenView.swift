//
//  LoginScreenView.swift
//  iosApp
//
//  Created by Ярослав Зотов on 19.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct LoginScreenView: View {
    
    var body: some View {
        VStack(spacing: 10) {
            LoginLogoView()
            LoginAppNameView().padding(.bottom, 6)
            LoginFormView()
        }.padding(.horizontal, 20)
    }
}

struct LoginScreenView_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreenView()
    }
}
