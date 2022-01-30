//
//  LessonSkeletonView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LessonSkeletonView: View {
    
    var body: some View {
        HStack(spacing: 0) {
            LessonCircleView(subject: "")
            
            VStack(alignment: .leading, spacing: 0) {
                LessonSubjectView("Физика типо")
                
                LessonSubtitleView("..................................")
            }.padding(.leading, 15)
        }
        .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
        .redacted(reason: .placeholder)
    }
}

struct LessonSkeletonView_Previews: PreviewProvider {
    static var previews: some View {
        LessonSkeletonView()
    }
}
