//
//  ScheduleLessonView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ScheduleLessonView: View {
    @EnvironmentObject var homeViewModel: HomeViewModel

    var lesson: Lesson
    var index: Int
    
    var body: some View {
        if homeViewModel.scheduleVisible {
            LessonWithRoomAndTimeView(lesson: lesson)
                .transition(.asymmetric(
                    insertion: .move(edge: .trailing),
                    removal: .move(edge: .leading)
                ).combined(with: .opacity).animation(.spring().delay(Double(index) * 0.05)))
                .animation(.spring().delay(Double(index) * 0.05))
        }
    }
}

struct ScheduleLessonView_Previews: PreviewProvider {
    
    static var previews: some View {
        let lesson = Lesson(
            id: 1,
            subject: "Физика",
            groupId: 1,
            room: "202",
            date: Kotlinx_datetimeLocalDateTime.init(
                year: 2022,
                month: Kotlinx_datetimeMonth.february,
                dayOfMonth: 21,
                hour: 15,
                minute: 3,
                second: 0,
                nanosecond: 0
            ),
            lessonNumber: 3,
            duration: 2323333
        )
        
        VStack {
            let homeViewModel: HomeViewModel = HomeViewModel()
            Button("toggle") { withAnimation { homeViewModel.scheduleVisible.toggle() } }
                ScheduleLessonView(lesson: lesson, index: 0).environmentObject(homeViewModel)
                ScheduleLessonView(lesson: lesson, index: 1).environmentObject(homeViewModel)
                ScheduleLessonView(lesson: lesson, index: 2).environmentObject(homeViewModel)
                ScheduleLessonView(lesson: lesson, index: 3).environmentObject(homeViewModel)
                ScheduleLessonView(lesson: lesson, index: 4).environmentObject(homeViewModel)
            
        }
            
    }
}

extension AnyTransition {
    static var backslide: AnyTransition {
        AnyTransition.asymmetric(
            insertion: .move(edge: .trailing),
            removal: .move(edge: .leading)
        )
        
    }
}
