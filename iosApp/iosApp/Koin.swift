//
//  Koin.swift
//  iosApp
//
//  Created by Ярослав Зотов on 26.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

func startKoin() {
    let koinApplication = IosKoinKt.doInitKoinIos()
    _koin = koinApplication.koin
}

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    return _koin!
}
