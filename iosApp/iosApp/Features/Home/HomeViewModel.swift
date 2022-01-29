//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 29.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class HomeViewModel: ObservableObject {
    @Published var scheduleVisible: Bool = false
    @Published var calendarDate: Kotlinx_datetimeLocalDate
    
    init() {
        scheduleVisible = true
        
        let now = Date()
        let calendar = Calendar.current
        calendarDate = Kotlinx_datetimeLocalDate(
            year: Int32(calendar.component(.year, from: now)),
            month: Kotlinx_datetimeMonth.values().get(index: Int32(calendar.component(.month, from: now) - 1))!,
            dayOfMonth: Int32(calendar.component(.day, from: now))
        )
    }
}
