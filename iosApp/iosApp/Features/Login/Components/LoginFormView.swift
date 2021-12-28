//
//  Form.swift
//  iosApp
//
//  Created by Ярослав Зотов on 19.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginFormView: View {
    @State private var email: String = ""
    @State private var password: String = ""
    @State private var error: String = ""
    @State private var loading: Bool = false
    
    private var isDisabled: Bool {
        get { return email.isEmpty || password.isEmpty || !error.isEmpty }
    }
    
    @EnvironmentObject private var authStatus: AuthStatusObservable
    
    var body: some View {
        VStack(spacing: 15) {
            TextInput(placeholder: "Email", text: $email)
                .textContentType(.emailAddress)
                .keyboardType(.emailAddress)
                .onChange(of: email, perform: { _ in
                    if !error.isEmpty { withAnimation { error = "" } }
                })
            
            SecureInput(placeholder: "Password", text: $password)
                .textContentType(.password)
                .onChange(of: password, perform: { _ in
                    if !error.isEmpty { withAnimation { error = "" } }
                })
            
            MainButton(
                "Login",
                action: {
                    loading = true
                    
                    authStatus.reducer.login(
                        email: email,
                        password: password
                    ) { exception, _ in
                        loading = false
                        if exception != nil {
                            withAnimation {
                                error = TransalateExceptionKt.translateException(exception: exception!)
                            }
                        } else {
                            withAnimation {
                                error = ""
                            }
                        }
                    }
                },
                isDisabled: isDisabled,
                isLoading: loading
            )
            
            
            LoginErrorText(error: error)
        }
    }
}

struct LoginFormView_Previews: PreviewProvider {
    static var previews: some View {
        LoginFormView()
    }
}
