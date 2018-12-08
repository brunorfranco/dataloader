package com.example.dataloader.runnable;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dataloader.repository.WineRepository;
import com.example.dataloader.repository.WineVarietyRepository;
import com.example.dataloader.repository.WineryRepository;
import com.example.dataloader.resource.Wine;
import com.example.dataloader.resource.WineVariety;
import com.example.dataloader.resource.Winery;

public class RunnableWineInsert implements Runnable{

	@Autowired
	WineRepository wineRepo;
	@Autowired
	WineryRepository wineryRepo;
	@Autowired
	WineVarietyRepository varietyRepo;
	@Override
	public void run() {
		Set<Wine> wines = new HashSet<>();
		
		int count = 0;
		Winery winery = wineryRepo.findById(427).get();
		WineVariety variety = varietyRepo.findById("Airen").get();
		while(count < 240000) {
			String wineName = RandomStringUtils.random(10, true, false);
			String designation = RandomStringUtils.random(10, true, false);
			Random r = new Random();
			double randomValue = 0.0 + (500 - 0) * r.nextDouble();
			
			wines.add(new Wine(wineName, designation, randomValue, winery, variety));
			count++;
		}
		for (Wine wine : wines) {
			if(wine.getWineName() != null) {
				System.out.println("Name:" + wine.getWineName() + " --- Winery:" + wine.getWinery().getName()
						+ " --- Variety:" + wine.getWineVariety().getVariety());
				wineRepo.save(wine);
			}
		}
	}
}
