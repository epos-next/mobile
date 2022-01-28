//
//  LessonCircleView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 29.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LessonCircleView: View {
    let subject: String
    
    private var color: Color {
        get {
            if (subject.lowercased().contains("физика")) { return Color(hex: 0xFFBBEDBF) }
            if (subject.lowercased().contains("геометрия")) { return Color(hex: 0xFFFBDDC3) }
            if (subject.lowercased().contains("география")) { return Color(hex: 0xFFFEF0C5) }
            if (subject.lowercased().contains("английский")) { return Color(hex: 0xFFC5CAFE) }
            if (subject.lowercased().contains("алгебра")) { return Color(hex: 0xFFF8CBC4) }
            if (subject.lowercased().contains("русский")) { return Color(hex: 0xFFE9B6FC) }
            if (subject.lowercased().contains("химия")) { return Color(hex: 0xFFC4EBFD) }
            if (subject.lowercased().contains("биология")) { return Color(hex: 0xFFc4d0fd) }
            if (subject.lowercased().contains("история")) { return Color(hex: 0xFFc4fdce) }
            if (subject.lowercased().contains("информатика")) { return Color(hex: 0xFFc4fdee) }
            if (subject.lowercased().contains("технология")) { return Color(hex: 0xFFc4fdee) }
            if (subject.lowercased().contains("литература")) { return Color(hex: 0xFFe8c4fd) }
            if (subject.lowercased().contains("математика")) { return Color(hex: 0xFFfddcc4) }
            if (subject.lowercased().contains("обществознание")) { return Color(hex: 0xFFfdc4ca) }
            if (subject.lowercased().contains("физкультура")) { return Color(hex: 0xFFfdc4d0) }
            return Color(hex: 0xFFcccccc)
        }
    }
    
    private var colorAccent: Color {
        get {
            if (subject.lowercased().contains("физика")) { return Color(hex: 0xFF68D676) }
            if (subject.lowercased().contains("геометрия")) { return Color(hex: 0xFFF5A664) }
            if (subject.lowercased().contains("география")) { return Color(hex: 0xFFFCCF62) }
            if (subject.lowercased().contains("английский")) { return Color(hex: 0xFF6D73FD) }
            if (subject.lowercased().contains("алгебра")) { return Color(hex: 0xFFF18477) }
            if (subject.lowercased().contains("русский")) { return Color(hex: 0xFFD46DF9) }
            if (subject.lowercased().contains("химия")) { return Color(hex: 0xFF83D4FC) }
            if (subject.lowercased().contains("биология")) { return Color(hex: 0xFF8387fc) }
            if (subject.lowercased().contains("история")) { return Color(hex: 0xFF6bd082) }
            if (subject.lowercased().contains("информатика")) { return Color(hex: 0xFF74debd) }
            if (subject.lowercased().contains("технология")) { return Color(hex: 0xFF74debd) }
            if (subject.lowercased().contains("литература")) { return Color(hex: 0xFFe483fc) }
            if (subject.lowercased().contains("математика")) { return Color(hex: 0xFFfcb983) }
            if (subject.lowercased().contains("обществознание")) { return Color(hex: 0xFFfc8383) }
            if (subject.lowercased().contains("физкультура")) { return Color(hex: 0xFFfc8393) }
            return Color(hex: 0xFF8f8f8f)
        }
    }
    
    var body: some View {
        ZStack {
            Circle()
                .fill(color)
                .frame(width: 36, height: 36)
            
            Text(subject.prefix(1).uppercased())
                .font(.custom("AvenirNext-Demi", size: 20))
                .foregroundColor(colorAccent)
        }
    }
}

struct LessonCircleView_Previews: PreviewProvider {
    static var previews: some View {
        LessonCircleView(subject: "физика")
    }
}
