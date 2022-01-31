//
//  LessonWithMarksView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LessonWithMarksView: View {
    var lessonName: String
    var marks: [Int]
    var totalMark: Int?
    var onTap: () -> Void
    
    func calcTotalExpected() -> Int {
        return !marks.isEmpty ? Int(round(Double(marks.reduce(0, +)) / Double(marks.count))) : 0
    }
    
    @ViewBuilder
    var body: some View {
        let totalExpected = calcTotalExpected()
        
        Button(action: { onTap() }) {
            HStack(spacing: 0) {
                LessonCircleView(subject: lessonName)
                
                VStack(alignment: .leading, spacing: 0) {
                    LessonSubjectView(lessonName)
                    HStack(spacing: 0) {
                        ForEach(marks, id: \.self) { mark in
                            LessonSubtitleView(String(mark))
                                .padding(.trailing, 20)
                        }
                    }
                }.padding(.leading, 15)
                
                Spacer()
                
                if totalExpected != 0 || totalMark != nil {
                    TotalNumber(number: totalMark ?? totalExpected, active: totalMark != nil)
                }
            }
            .frame(minWidth: 0, maxWidth: .infinity)
            .padding(.horizontal, 20)
            .padding(.vertical, 16)
        }.buttonStyle(TappableButtonStyle())
    }
}

private struct TotalNumber: View {
    var number: Int
    var active: Bool = true
    
    @ViewBuilder
    var body: some View {
        let color = active ? Color.contrast : Color.lightContrast
        
        Text(String(number))
            .font(.custom("AvenirNext-Medium", size: 18))
            .foregroundColor(color)
    }
}

struct LessonWithMarksView_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            LessonWithMarksView(lessonName: "Физика", marks: [5, 4, 5, 3, 2], totalMark: 4, onTap: {})
            LessonWithMarksView(lessonName: "Физика", marks: [5, 4, 5, 3, 2], totalMark: nil, onTap: {})
        }
    }
}
