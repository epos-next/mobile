//
//  SchedulePartView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SchedulePartView: View {
    @EnvironmentObject var scheduleObservable: ScheduleObservable
    @EnvironmentObject var homeViewModel: HomeViewModel
        
    var body: some View {
        VStack {
            CalendarView(onDaySelected: { date in
                scheduleObservable.reducer.loadDateSchedule(
                    date: date,
                    completionHandler: { amount, error in
                        if amount != 0 {
                            homeViewModel.resetScheduleVisible()
                        }
                    })
            }).padding(.top, 20)
            
            scheduleList
        }
    }

    @ViewBuilder
    private var scheduleList: some View {
        let state = scheduleObservable.state
        
        if state is ScheduleState.Idle || state is ScheduleState.Loading {
            let lessons = (state as? ScheduleState.Idle)?.lessons ?? scheduleObservable.prevScheduleList
            
            VStack {
                ForEach(0..<lessons.count, id: \.self) { i in
                    if state is ScheduleState.Idle {
                        ScheduleLessonView(lesson: lessons[i], index: i)
                    } else {
                        LessonSkeletonView()
                            .redacted(reason: .placeholder)
                    }
                }
            }.padding(.horizontal, 20)
        }
        else if let message = (state as? ScheduleState.Error)?.message {
            TextErrorView(error: message)
        }
        else if state is ScheduleState.ItsSummer {
            NowSummerView()
        }
        else {
            EmptyView()
        }
    }
}
