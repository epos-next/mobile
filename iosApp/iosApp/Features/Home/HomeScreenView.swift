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
            VStack {
                SchedulePartView()
                Text("*Какой-то еще контент внизу*")
            }.animation(.spring())
        }
    }
}
