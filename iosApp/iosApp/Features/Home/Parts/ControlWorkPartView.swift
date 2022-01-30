//
//  ControlWorkPartView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ControlWorkPartView: View {
    @EnvironmentObject var observable: ControlWorkObservable
    
    @ViewBuilder
    var body: some View {
        TitleWithCreateButtonView(text: "Контрольные работы", onTap: { }).padding(.horizontal, 20)
        
        let state = observable.state
        
        if let tests = (state as? SchoolTestsState.Idle)?.tests {
            VStack(spacing: 10) {
                ForEach(tests, id: \.self ) { test in
                    ControlWorkView(controlWork: test)
                }
            }.padding(.horizontal, 20)
        } else if let message = (state as? SchoolTestsState.Error)?.message {
            TextErrorView(error: message).padding(.top, 10)
        } else if state is SchoolTestsState.Loading {
            ForEach(0...3, id: \.self) { _ in
                LessonSkeletonView()
            }.padding(.horizontal, 20)
        }
    }
}
