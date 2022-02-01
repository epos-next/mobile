//
//  NextLessonPartView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NextLessonPartView: View {
    @EnvironmentObject var observable: NextLessonObservable
    
    @ViewBuilder
    var body: some View {
        if let nextLesson = (observable.state as? NextLessonState.Idle)?.nextLesson {
            AnimatedTitle(state: observable.state)
            
            LessonWithRoomAndTimeView(lesson: nextLesson)
        } else {
            EmptyView()
        }
        
    }
}

private struct AnimatedTitle: View {
    var state: NextLessonState
    
    var body: some View {
        Title(state: state)
            .transition(
                .asymmetric(
                    insertion: .move(edge: .trailing),
                    removal: .move(edge: .leading)
                )
                    .combined(with: .opacity)
                    .animation(.spring()))
            .animation(.spring())
    }
}


private struct Title: View {
    var state: NextLessonState
    
    @ViewBuilder
    var body: some View {
        if state is NextLessonState.Break {
            HomeTitleView(text: "Следующий урок")
        } else if state is NextLessonState.LessonTime {
            HomeTitleView(text: "Сейчас идет")
        }
    }
}
