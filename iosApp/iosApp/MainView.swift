//
//  MainView.swift
//  iosApp
//
//  Created by Ярослав Зотов on 27.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import BottomSheet


struct MainView: View {
    @State var selectedTab: String = "home"
    
    @ObservedObject var bottomSheetObservable = BottomSheetObservable()
    @Environment(\.colorScheme) var colorScheme

    
    init() {
        UITabBar.appearance().isHidden = false
    }
    
    private var bg: Color {
        get {
            colorScheme == .light ? Color.white : Color(hex: 0xFF0F0F17)
        }
    }
    
    var body: some View {
        ZStack(alignment: .bottom) {
            TabView (selection: $selectedTab) {
                HomeScreenView().tag("home")
                MarksScreenView().tag("marks").background(bg)
                ProfileScreenView().tag("profile").background(bg)
            }
            TabBarView(selectedTab: $selectedTab)
        }
        .ignoresSafeArea()
        .navigationTitle("")
        .navigationBarHidden(true)
        .primaryBottomSheet(position: $bottomSheetObservable.advertisement, colorScheme: colorScheme) { CreateAdSheet() }
        .primaryBottomSheet(position: $bottomSheetObservable.controlWork, colorScheme: colorScheme) { CreateTestReminderSheet() }
        .environmentObject(bottomSheetObservable)
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
            .environmentObject(HomeViewModel())
    }
}
