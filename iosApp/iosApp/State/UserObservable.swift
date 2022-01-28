//
//  AuthStatusObservable.swift
//  iosApp
//
//  Created by Ярослав Зотов on 26.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class UserObservable: ObservableObject {
     @Published var state: UserState = UserState.Loading()
     @Published var tag: String? = "loading"

     let reducer: UserReducer

     init() {
         reducer = UserReducer()
         reducer.onChange { [weak self] newState in
             self?.state = newState!
             if (newState is UserState.Authorized) { self?.tag = "authorized" }
             if (newState is UserState.NotAuthorized) { self?.tag = "not-authorized" }
             if (newState is UserState.Loading) { self?.tag = "loading" }
         }
     }
}
