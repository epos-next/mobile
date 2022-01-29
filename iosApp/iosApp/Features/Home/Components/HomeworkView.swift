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
    
    @State var checked: Bool = true
    
    var body: some View {
        Button(action: { checked.toggle() }) {
            HStack(spacing: 0) {
                LessonCircleView(subject: homework.lesson)
                
                VStack(alignment: .leading, spacing: 0) {
                    LessonSubjectView(homework.lesson)
                    LessonSubtitleView(homework.content)
                }.padding(.leading, 15)
                
                Spacer()
                
                Toggle("", isOn: $checked)
                    .toggleStyle(CheckboxStyle())
            }
            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
        }.buttonStyle(WrapperButtonStyle())
        
    }
}

private struct WrapperButtonStyle: ButtonStyle {
    func makeBody(configuration: Self.Configuration) -> some View {
        return configuration.label
            .scaleEffect(configuration.isPressed ? 0.99 : 1.0)
    }
}

struct CheckboxStyle: ToggleStyle {

    func makeBody(configuration: Self.Configuration) -> some View {
        return HStack {
            Image(systemName: configuration.isOn ? "checkmark.square.fill" : "square")
                .resizable()
                .animation(.spring())
                .frame(width: 24, height: 24)
                .foregroundColor(configuration.isOn ? Color(hex: 0xFF68D676) : Color.lightPrimary)
                .font(.system(size: 20, weight: .regular, design: .default))
                configuration.label
                
        }
        .onTapGesture { configuration.isOn.toggle() }

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
