//
//  HomeScreenView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 30.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreenView: View {
    var body: some View {
        ScrollView {
            VStack(spacing: 15) {
                NextLessonPartView()
                SchedulePartView()
                HomeworkPartView()
                ControlWorkPartView()
                AdvertisementPartView()
            }.animation(.spring())
        }
    }
}
