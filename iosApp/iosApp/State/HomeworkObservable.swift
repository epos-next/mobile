//
//  HomeworkObservable.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class HomeworkObservable: ObservableObject {
    @Published var state: HomeworkState = HomeworkState.Idle(homework: [])
    var reducer: HomeworkReducer
    
    init() {
        reducer = HomeworkReducer()
        reducer.onChange { [weak self] newState in
            DispatchQueue.main.async {
                self?.state = newState! as! HomeworkState
            }
        }
    }
}
