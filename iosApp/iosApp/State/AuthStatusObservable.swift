//
//  AuthStatusObservable.swift
//  iosApp
//
//  Created by Ярослав Зотов on 26.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class AuthStatusObservable: ObservableObject {
    @Published var state: AuthStatusState = AuthStatusState.Loading()
    @Published var tag: String? = "loading"
    
    let reducer: AuthStatusReducer
    
    init() {
        reducer = AuthStatusReducer()
        reducer.onChange { [weak self] newState in
            self?.state = newState!
            if (newState is AuthStatusState.Authorized) { self?.tag = "authorized" }
            if (newState is AuthStatusState.NotAuthorized) { self?.tag = "not-authorized" }
            if (newState is AuthStatusState.Loading) { self?.tag = "loading" }
        }
    }
}
