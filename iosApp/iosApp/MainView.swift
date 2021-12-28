//
//  MainView.swift
//  iosApp
//
//  Created by Ярослав Зотов on 27.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct HomeView: View {
    var body: some View {
        Text("Home")
    }
}

struct MarksView: View {
    var body: some View {
        Text("Marks")
    }
}

struct ProfileView: View {
    @EnvironmentObject var authStatus: AuthStatusObservable
    
    init() {
        print("test")
    }
    
    var body: some View {
        Button("logout") {
            authStatus.reducer.logout()
        }
    }
}

struct MainView: View {
    @State var selectedTab: String
    
    init() {
        UITabBar.appearance().isHidden = false
        selectedTab = "home"
    }
    
    var body: some View {
        ZStack(alignment: .bottom) {
            TabView (selection: $selectedTab) {
                HomeView().tag("home")
                MarksView().tag("marks")
                ProfileView().tag("profile")
            }
            TabBarView(selectedTab: $selectedTab)
        }.ignoresSafeArea()
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
