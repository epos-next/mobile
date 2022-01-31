//
//  HomeworkView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeworkView: View {
    var homework: Homework
    var onTap: (_ status: Bool) -> Void
        
    var body: some View {
        Button(action: { onTap(!homework.done) }) {
            HStack(spacing: 0) {
                LessonCircleView(subject: homework.lesson)
                
                VStack(alignment: .leading, spacing: 0) {
                    LessonSubjectView(homework.lesson)
                    LessonSubtitleView(homework.content)
                }.padding(.leading, 15)
                
                Spacer()
                
                Image(systemName: homework.done ? "checkmark.square.fill" : "square")
                    .resizable()
                    .animation(.spring())
                    .frame(width: 24, height: 24)
                    .foregroundColor(homework.done ? Color(hex: 0xFF68D676) : Color.lightPrimary)
                    .font(.system(size: 20, weight: .regular, design: .default))
            }
            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
        }.buttonStyle(TappableButtonStyle())
        
    }
}

struct HomeworkView_Previews: PreviewProvider {
    static var previews: some View {
        let homework = Homework(
            id: 1,
            lesson: "Физика",
            content: "Прочитать какой-нибудь параграф или типо того",
            done: true,
            date: Kotlinx_datetimeLocalDateTime.init(
                year: 2022,
                month: Kotlinx_datetimeMonth.february,
                dayOfMonth: 21,
                hour: 15,
                minute: 3,
                second: 0,
                nanosecond: 0
            )
        )
        
        HomeworkView(homework: homework, onTap: { _ in })
    }
}
