//
//  LoginErrorText.swift
//  iosApp
//
//  Created by Ярослав Зотов on 28.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct LoginErrorText: View {
    var error: String
    
    private let errorAnimation: AnyTransition = .asymmetric(
        insertion: .move(edge: .bottom).combined(with: .opacity),
        removal: .move(edge: .top).combined(with: .opacity)
    )
    
    private let hintAnimation: AnyTransition = .asymmetric(
        insertion: .move(edge: .top).combined(with: .opacity),
        removal: .move(edge: .bottom).combined(with: .opacity)
    )
    
    var body: some View {
        if (error.isEmpty) {
            Text("Email и пароль от аккаунта ЭПОС.Школа")
                .font(.custom("AvenirNext-Medium", size: 14))
                .foregroundColor(.lightPrimary)
                .multilineTextAlignment(.center)
                .animation(.spring())
                .transition(hintAnimation)
        } else {
            Text(error)
                .font(.custom("AvenirNext-Medium", size: 14))
                .foregroundColor(.error)
                .multilineTextAlignment(.center)
                .animation(.spring())
                .transition(errorAnimation)
        }
    }
}

struct LoginErrorText_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            LoginErrorText(error: "Some error")
            LoginErrorText(error: "")
        }
    }
}
