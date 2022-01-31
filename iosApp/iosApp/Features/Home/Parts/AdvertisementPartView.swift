//
//  AdvertisementPartView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AdvertisementPartView: View {
    @EnvironmentObject var observable: AdvertisementObservable
    
    @ViewBuilder
    var body: some View {
        let state = observable.state
        
        TitleWithCreateButtonView(text: "Объявления", onTap: {}).padding(.horizontal, 20)
        
        if let advertisements = (state as? AdvertisementsState.Idle)?.advertisements {
            VStack(spacing: 10) {
                ForEach(advertisements, id: \.self) { ad in
                    AdvertisementComponentView(ad)
                }
            }.padding(.horizontal, 20)
        } else if let message = (state as? AdvertisementsState.Error)?.message {
            TextErrorView(error: message)
        } else if state is AdvertisementsState.Loading {
            ForEach(0...3, id: \.self) { _ in
                AdvertisementComponentSkeletonView()
            }.padding(.horizontal, 20)
        }
    }
}
