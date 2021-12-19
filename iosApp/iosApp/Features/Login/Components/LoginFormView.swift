//
//  Form.swift
//  iosApp
//
//  Created by Ярослав Зотов on 19.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct LoginFormView: View {
    @State private var email: String = ""
    @State private var password: String = ""
    
    var body: some View {
        VStack(spacing: 15) {
            TextInput(placeholder: "Email", text: $email)
                .textContentType(.emailAddress)
                .keyboardType(.emailAddress)
            
            SecureInput(placeholder: "Password", text: $password)
                .textContentType(.password)
            
            MainButton("Login", action: {})
            
            Text("Email и пароль от аккаунта ЭПОС.Школа")
                .font(.custom("AvenirNext-Medium", size: 14))
                .foregroundColor(.lightPrimary)
                .multilineTextAlignment(.center)
            
        }
    }
}

struct LoginFormView_Previews: PreviewProvider {
    static var previews: some View {
        LoginFormView()
    }
}
