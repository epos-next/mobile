//
//  LessonWithRoomAndTime.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LessonWithRoomAndTimeView: View {
    var lesson: Lesson
    
    @ViewBuilder
    var body: some View {
        HStack(spacing: 0) {
            LessonCircleView(subject: lesson.subject)
            
            VStack(alignment: .leading, spacing: 0) {
                LessonSubjectView(lesson.subject)
                
                HStack {
                    LessonSubtitleView(lesson.room)
                    LessonSubtitleDotView()
                    LessonSubtitleView(FormatHelper.shared.formatLessonTime(startDate: lesson.date, duration: lesson.duration))
                    
                    Spacer()
                    
                    if lesson.truancy {
                        TruancyView()
                    }
                    
                    ForEach(0 ..< lesson.marks.count, id: \.self) { index in
                        TotalNumber(number: Int(truncating: lesson.marks[index]))
                    }
                }
            }.padding(.leading, 15)
        }.frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
    }
}

private struct TruancyView: View {
    var body: some View {
        Text("H")
            .font(.custom("AvenirNext-Regular", size: 18))
            .foregroundColor(Color(hex: 0xFFff6700))
    }
}

struct LessonWithRoomAndTime_Previews: PreviewProvider {
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
            duration: 2323333,
            marks: [],
            truancy: false
        )
        LessonWithRoomAndTimeView(lesson: lesson)
    }
}
