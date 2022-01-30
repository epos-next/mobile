//
//  HomeworkPartView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeworkPartView: View {
    @EnvironmentObject var homeworkObservable: HomeworkObservable
    
    @ViewBuilder
    var body: some View {
        let state = homeworkObservable.state
        
        if let homework = (state as? HomeworkState.Idle)?.homework {
            if !homework.isEmpty {
                VStack {
                    HomeTitleView(text: "Домашнее задание")
                    
                    ForEach(homework, id: \.self) { hw in
                        HomeworkView(homework: hw, onTap: { done in
                            homeworkObservable.reducer.updateDone(id: hw.id, done: done)
                        })
                    }
                }
                .padding(.horizontal, 20)
                .padding(.top, 10)
            } else {
                EmptyView()
            }
        } else if let message = (state as? HomeworkState.Error)?.message {
            TextErrorView(error: message)
        } else {
            VStack {
                HomeTitleView(text: "Домашнее задание")
                ForEach(0...3, id: \.self) { _ in
                    LessonSkeletonView()
                }
            }
            .padding(.horizontal, 20)
            .padding(.top, 10)
        }
    }
}


