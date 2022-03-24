//
//  ScheduleObservable.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class ScheduleObservable: ObservableObject {
    @Published var state: ScheduleState = ScheduleState.Loading()
    var reducer: ScheduleReducer
    
    @Published var prevScheduleList: [Lesson] = []
    
    init() {
        reducer = ScheduleReducer()
        reducer.onChange { [weak self] newState in
            self?.state = newState!
            print(self?.state)
            
            if let lessons = (newState as? ScheduleState.Idle)?.lessons {
                self?.prevScheduleList = lessons
            }
        }
    }
}
