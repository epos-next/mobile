//
//  AdvertisementObservable.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class AdvertisementObservable: ObservableObject {
    
    @Published var state: AdvertisementsState = AdvertisementsState.Loading()
    let reducer: AdvertisementsReducer
    
    init() {
        reducer = AdvertisementsReducer()
        reducer.onChange { [weak self] state in
            DispatchQueue.main.async {
                self?.state = state  as! AdvertisementsState
            }
        }
    }
}
