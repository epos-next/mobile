//
//  ControlWorkObservable.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class ControlWorkObservable: ObservableObject {
    @Published var state: SchoolTestsState = SchoolTestsState.Loading()
    let reducer: SchoolTestsReducer
    
    init() {
        reducer = SchoolTestsReducer()
        reducer.onChange { [weak self] state in
            print(state)
            DispatchQueue.main.async {
                self?.state = state! as! SchoolTestsState
            }
        }
    }
}
