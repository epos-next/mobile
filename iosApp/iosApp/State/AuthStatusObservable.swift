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
    
    let reducer: AuthStatusReducer
    
    init() {
        reducer = AuthStatusReducer()
        reducer.onChange { [weak self] newState in
            self?.state = newState!
        }
    }
}
