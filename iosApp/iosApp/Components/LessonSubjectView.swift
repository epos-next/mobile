//
//  LessonSubject.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 29.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LessonSubjectView: View {
    let subject: String
    
    init(_ subject: String) {
        self.subject = subject
    }
    
    private var formattedSubject: String {
        let lowercased = subject.lowercased()
        if lowercased.contains("основы безопасности жизнедеятельности") { return "ОБЖ" }
        if lowercased.contains("изобразительное искусство") { return "ИЗО" }
        if lowercased.contains("английский язык") { return "Английский язык" }
        if lowercased.contains("история") { return "История" }
        if lowercased.contains("физическая культура") { return "Физкультура" }
        else { return subject }
    }
    
    var body: some View {
        Text(formattedSubject)
            .font(.custom("AvenirNext-Regular", size: 20))
            .foregroundColor(Color.textPrimary)
    }
}

struct LessonSubject_Previews: PreviewProvider {
    static var previews: some View {
        LessonSubjectView("Физика")
    }
}
