//
//  LessonSubtitleView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 29.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LessonSubtitleView: View {
    let text: String
    
    init(_ text: String) {
        self.text = text
    }
    
    var body: some View {
        Text(text)
            .font(.custom("AvenirNext-Regular", size: 14))
            .foregroundColor(Color.secondary)
            .fixedSize(horizontal: false, vertical: true)
    }
}

struct LessonSubtitleDotView: View {
    var body: some View {
        Circle()
            .padding(10)
            .frame(width: 3, height: 3)
            .background(Color.secondary)
    }
}

struct LessonSubtitleView_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            LessonSubtitleView("Какой-то сабтайтл")
            
            HStack {
                LessonSubtitleDotView()
                LessonSubtitleView("Какой-то сабтайтл c точкой")
            }
        }
    }
}
