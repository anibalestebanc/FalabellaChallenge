package com.falabella.falabellachallenge.data.database.economicindicator

import com.falabella.falabellachallenge.data.database.LocalDatabase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Created by Anibal Cortez on 10/8/20.
 */
class EconomicIndicatorLocalDataSourceImplTest {


    private val economicIndicatorDao: EconomicIndicatorDao = mock()
    private val localDataBase: LocalDatabase = mock()
    private val localDataSourceImpl = EconomicIndicatorLocalDataSourceImpl(localDataBase)

   /* @Test
    fun `If local database count is 0 then return economic indicator list is empty`() {
        runBlocking {

            whenever(localDataBase.economicIndicatorDao()).thenReturn(economicIndicatorDao)
            whenever(economicIndicatorDao.count()).thenReturn(0)

            val result = localDataSourceImpl.isEconomicIndicatorListEmpty()
            assertTrue(result)
        }
    }
    @Test
    fun validate(){ // if the local database count > 0 then there is elements in the list
       runBlocking {

           whenever(localDataBase.economicIndicatorDao()).thenReturn(economicIndicatorDao)
           whenever(economicIndicatorDao.count()).thenReturn(1)

           val result = localDataSourceImpl.isEconomicIndicatorListEmpty()
           assertFalse(result)
       }

    } */
}