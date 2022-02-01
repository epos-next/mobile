//
//  NextLessonObservable.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class NextLessonObservable: ObservableObject {
    @Published var state: NextLessonState = NextLessonState.Loading()
    let reducer: NextLessonReducer
    
    init() {
        reducer = NextLessonReducer()
        reducer.onChange { [weak self] state in
            self?.state = state!
        }
    }
}
