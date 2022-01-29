//
//  LessonTimeLeftView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 29.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//
    
import SwiftUI
import shared

struct LessonTimeLeftView: View {
    let timeLeft: Int64
    
    init(_ timeLeft: Int64) {
        self.timeLeft = timeLeft
    }
    
    var body: some View {
        Text(UtilsKt.formatTimeLeft(duration: timeLeft))
            .font(.custom("AvenirNext-Regular", size: 20))
            .foregroundColor(Color.contrast)
    }
}

struct LessonTimeLeftView_Previews: PreviewProvider {
    static var previews: some View {
        LessonTimeLeftView(20)
    }
}
    
