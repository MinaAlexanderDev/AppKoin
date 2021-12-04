package com.mina.localdatabaseapp.model

import com.mina.localdatabaseapp.model.localdb.roomdb.DatabaseRepository
import com.mina.localdatabaseapp.remote.RemoteRepository

interface Repository:RemoteRepository,DatabaseRepository {

}