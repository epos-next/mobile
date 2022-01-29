//
//  ControlWorkView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ControlWorkView: View {
    var controlWork: ControlWork
    
    var body: some View {
        HStack(spacing: 0) {
            LessonCircleView(subject: controlWork.lesson)
            
            VStack(alignment: .leading, spacing: 0) {
                LessonSubjectView(controlWork.lesson)
                LessonSubtitleView(controlWork.name)
            }.padding(.leading, 15)
            
            Spacer()
            
            Text(FormatHelper.shared.futureDate(date: controlWork.date))
                .font(.custom("AvenirNext-Regular", size: 13))
                .foregroundColor(Color.secondary)
            
        }.frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
    }
}

struct ControlWorkView_Previews: PreviewProvider {
    static var previews: some View {
        let controlWork = ControlWork(
            id: 1,
            lesson: "Физика",
            date: Kotlinx_datetimeLocalDateTime.init(
                year: 2022,
                month: Kotlinx_datetimeMonth.february,
                dayOfMonth: 21,
                hour: 15,
                minute: 3,
                second: 0,
                nanosecond: 0
            ),
            name: "Контрольная по динамике"
        )
        ControlWorkView(controlWork: controlWork)
    }
}
