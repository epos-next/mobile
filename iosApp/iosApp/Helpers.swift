//
//  Helpers.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared


func dateToKtx(_ date: Date) -> Kotlinx_datetimeLocalDateTime {
    let calendarDate = Calendar.current.dateComponents([.day, .year, .month, .hour, .minute, .second], from: date)
    return Kotlinx_datetimeLocalDateTime(
        year: Int32(calendarDate.year!),
        month: Kotlinx_datetimeMonth.values().get(index: Int32(calendarDate.month!) - 1)!,
        dayOfMonth: Int32(calendarDate.day!),
        hour: Int32(calendarDate.hour ?? 0),
        minute: Int32(calendarDate.minute ?? 0),
        second: Int32(calendarDate.second ?? 0),
        nanosecond: 0
    )
}

func KtxToDate(_ date: Kotlinx_datetimeLocalDateTime) -> Date {
    var dateComponents = DateComponents()
    dateComponents.year = Int(date.year)
    dateComponents.month = Int(date.monthNumber)
    dateComponents.day = Int(date.dayOfMonth)
    dateComponents.hour = Int(date.hour)
    dateComponents.minute = Int(date.minute)
    dateComponents.second = Int(date.second)
    
    return Calendar(identifier: .gregorian).date(from: dateComponents)!
}
