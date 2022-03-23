//
//  DarkModeObservable.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared

class DarkModeObservable: ObservableObject {
    @Published var state: Bool = false
    let reducer: DarkModeReducer
    
    init() {
        reducer = DarkModeReducer()
        reducer.onChange() { [weak self] state in
            DispatchQueue.main.async {
                self?.state = state?.boolValue ?? false
            }
        }
    }
}

