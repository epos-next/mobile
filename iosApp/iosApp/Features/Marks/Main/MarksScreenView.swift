//
//  MarksScreenView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MarksScreenView: View {
    @EnvironmentObject var observable: MarksObservable
    @State private var searchText: String = ""
    
    func getValidLesson() -> [String: MarkUnit] {
        if let state = (observable.state as? MarksState.Idle) {
            if searchText.isEmpty {
                return state.marks
            }
            return state.marks.filter { $0.key.contains(searchText) }
        } else {
            return [:]
        }
    }
    
    @ViewBuilder
    var body: some View {
        let lessons = getValidLesson()
        let keys = lessons.map { $0.key }.sorted()
        
        NavigationView {
            ScrollView {
                VStack {
                    FilledInputView(
                        text: $searchText,
                        placeholder: "Поиск",
                        suffixIcon: "search_icon"
                    ).padding(.horizontal, 20)
                    
                    ForEach(keys, id: \.self) { lesson in
                        LessonView(lesson: lesson, unit: lessons[lesson]!)
                    }
                }
            }
            .navigationBarHidden(true)
        }
        
    }
}

private struct LessonView: View {
    var lesson: String
    var unit: MarkUnit
    
    @ViewBuilder
    var body: some View {
        if unit.periods.isEmpty {
            EmptyView()
        } else {
            let marks = unit.periods.last?.all.map { Int($0.value) } ?? []
            
            NavigationLink(destination: DetailMarksScreenView(lesson: lesson, unit: unit )) {
                LessonWithMarksView(
                    lessonName: lesson,
                    marks: marks,
                    totalMark: unit.periods.last?.total != nil ? Int(truncating: unit.periods.last!.total!) : nil,
                    onTap: { }
                )
            }.buttonStyle(TappableButtonStyle())
        }
    }
}

struct MarksScreenView_Previews: PreviewProvider {
    static var previews: some View {
        MarksScreenView()
    }
}
