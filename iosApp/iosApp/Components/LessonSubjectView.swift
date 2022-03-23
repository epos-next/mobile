//
//  LessonSubject.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 29.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LessonSubjectView: View {
    let subject: String
    
    init(_ subject: String) {
        self.subject = subject
    }
    
    var body: some View {
        Text(FormatHelper.shared.formatSubjectName(name: subject))
            .font(.custom("AvenirNext-Regular", size: 16))
            .foregroundColor(Color.textPrimary)
    }
}

struct LessonSubject_Previews: PreviewProvider {
    static var previews: some View {
        LessonSubjectView("Физика")
    }
}
