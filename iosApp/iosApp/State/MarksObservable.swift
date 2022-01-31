//
//  MarksObservable.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class MarksObservable: ObservableObject {
    @Published var state: MarksState = MarksState.Loading()
    let reducer: MarksReducer
    
    init() {
        reducer = MarksReducer()
        reducer.onChange() { [weak self] state in
            DispatchQueue.main.async {
                self?.state = state!
            }
        }
    }
}
