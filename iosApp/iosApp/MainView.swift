//
//  MainView.swift
//  iosApp
//
//  Created by Ярослав Зотов on 27.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import BottomSheet

struct ProfileView: View {
    //    @EnvironmentObject var user: UserObservable
    
    init() {
        print("test")
    }
    
    var body: some View {
        Button("logout") {
            //            user.reducer.logout()
        }
    }
}

struct MainView: View {
    @State var selectedTab: String
    
    @State var bottomSheetPosition: BottomSheetPosition = .middle
    
    init() {
        UITabBar.appearance().isHidden = false
        selectedTab = "home"
    }
    
    let backgroundColors: [Color] = [Color(red: 0.17, green: 0.17, blue: 0.33), Color(red: 0.80, green: 0.38, blue: 0.2)]
    let songs: [String] = ["One Dance (feat. Wizkid & Kyla)", "God's Plan", "SICKO MODE", "In My Feelings", "Work (feat. Drake)", "Nice For What", "Hotline Bling", "Too Good (feat. Rihanna)", "Life Is Good (feat. Drake)"]

    
    var body: some View {
        ZStack(alignment: .bottom) {
            TabView (selection: $selectedTab) {
                HomeScreenView().tag("home")
                MarksScreenView().tag("marks")
                ProfileView().tag("profile")
            }
            TabBarView(selectedTab: $selectedTab)
        }
        .ignoresSafeArea()
        .navigationTitle("")
        .navigationBarHidden(true)
        .primaryBottomSheet(position: $bottomSheetPosition) { CreateTestReminderSheet() }
        
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
            .environmentObject(HomeViewModel())
    }
}
