package com.example.dataloader;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.dataloader.repository.CountryRepository;
import com.example.dataloader.repository.ProvinceRepository;
import com.example.dataloader.repository.ReviewRepository;
import com.example.dataloader.repository.TasterRepository;
import com.example.dataloader.repository.WineRepository;
import com.example.dataloader.repository.WineVarietyRepository;
import com.example.dataloader.repository.WineryRepository;
import com.example.dataloader.resource.Country;
import com.example.dataloader.resource.Province;
import com.example.dataloader.resource.Review;
import com.example.dataloader.resource.Taster;
import com.example.dataloader.resource.Wine;
import com.example.dataloader.resource.WineVariety;
import com.example.dataloader.resource.Winery;

@SpringBootApplication
public class DataloaderApplication {

	private static final String FILE_NAME = "C:\\Users\\Bruno\\Desktop\\Masters\\AdvancedDatabases\\assignment2\\winemag-data-130k-v2-2.csv";

	public static void main(String[] args) {
		SpringApplication.run(DataloaderApplication.class, args);
	}

	@Bean
	CommandLineRunner CommandLineRunner(WineVarietyRepository wr) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Set<String> wineVariety = new HashSet<>();
				try (
			            Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME));
			            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			        ) {
			            for (CSVRecord csvRecord : csvParser) {
			                wineVariety.add(csvRecord.get(0));
			            }
			        }
				
				for (String string : wineVariety) {
					System.out.println("Unique wine variety:" + string);
					wr.save(new WineVariety(string));
				}
			}
		};
	}
	
	@Bean
	CommandLineRunner CommandLineRunner(CountryRepository tr) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Set<Country> countries = new HashSet<>();
				try (Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME));
						CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
					for (CSVRecord csvRecord : csvParser) {
						String name = "";
						if (csvRecord.get(1) == null || csvRecord.get(1).trim().equals("")) {
							continue;
						} else {
							name = csvRecord.get(1);
						}
						countries.add(new Country(name));
					}
				}
				
				for (Country country : countries) {
					if(country.getName() != null) {
						System.out.println("Name:" + country.getName());
						tr.save(country);
					}
				}
			}
		};
	}
	
	@Bean
	CommandLineRunner CommandLineRunner(WineryRepository tr, CountryRepository cr) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Set<Winery> wineries = new HashSet<>();
				try (Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME));
						CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
					for (CSVRecord csvRecord : csvParser) {
						String wineryName = "";
						String countryName = null;
						Country country = null;
						if (csvRecord.get(13) == null || csvRecord.get(13).trim().equals("")) {
							continue;
						} else {
							wineryName = csvRecord.get(13);
							countryName = csvRecord.get(1);
							if(countryName == null || countryName.trim().equals("")) {
								countryName = "None";
							}
						}
						if(countryName != null) {
							country = cr.findById(countryName).get();
							if(country != null) {
								wineries.add(new Winery(wineryName, country));
							}
						}
					}
				}
				
				for (Winery winery : wineries) {
					if(winery.getName() != null) {
						System.out.println("Name:" + winery.getName()
						+" -- Country:" + winery.getCountry().getName());
						tr.save(winery);
					}
				}
			}
		};
	}
	
	@Bean
	CommandLineRunner CommandLineRunner(ReviewRepository reviewR, TasterRepository tr, WineRepository wineR) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Set<Review> reviews = new HashSet<>();
				try (Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME));
						CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
					for (CSVRecord csvRecord : csvParser) {
						String description = "";
						double points = 0.0;
						Wine wine = null;
						String wineName = "";
						Taster taster = null;
						String tasterTwitterHandle = "";
						
						//todo stopped here
						if (csvRecord.get(4) == null || csvRecord.get(4).trim().equals("")) {
							continue;
						} else {
							description = csvRecord.get(2);
							if(StringUtils.isNumeric(csvRecord.get(4))) {
								 points = Double.parseDouble(csvRecord.get(4));
							}
							if(csvRecord.get(11) != null && !csvRecord.get(11).trim().equals("")) {
								wineName = csvRecord.get(11);
							}
							if(csvRecord.get(10) != null && !csvRecord.get(10).trim().equals("")) {
								tasterTwitterHandle = csvRecord.get(10);;
							}
						}
						if(wineName != null) {
							List<Wine> wineList = wineR.findByWineName(wineName);
							if(!CollectionUtils.isEmpty(wineList)) {
								wine = wineR.findByWineName(wineName).get(0);
							}
						}
						if(tasterTwitterHandle != null) {
							List<Taster> twitterList = tr.findByTwitterhandletaster(tasterTwitterHandle);
							if(!CollectionUtils.isEmpty(twitterList)) {
								taster = twitterList.get(0);
							}
						}
						reviews.add(new Review(description, points, wine, taster));
					}
				}
				
				for (Review review : reviews) {
					if(review.getDescriptionReview() != null) {
						System.out.println("Review description:" + review.getDescriptionReview()
						+" -- Points:" + review.getPoints());
						if(review.getTaster() != null) {
							System.out.println(" -- Taster name:" + review.getTaster().getTwitterhandlertaster());
						}
						if(review.getWine() != null) {
							System.out.println(" -- Wine name:" + review.getWine().getWineName()	);
						}
						reviewR.save(review);
					}
				}
				
				System.out.println(" -------------- FINISHED -------------- ");
			}
		};
	}
	
	@Bean
	CommandLineRunner CommandLineRunner(TasterRepository tr) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Set<Taster> tasters = new HashSet<>();
				try (Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME));
						CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
					for (CSVRecord csvRecord : csvParser) {
						String name = "";
						String twitter = "";
						if (csvRecord.get(9) == null || csvRecord.get(9).trim().equals("")) {
							continue;
						} else {
							name = csvRecord.get(9);
						}
						if (csvRecord.get(10) == null || csvRecord.get(10).trim().equals("")) {
							twitter = "NoTwitterHandler";
						} else {
							twitter = csvRecord.get(10);
						}
						tasters.add(new Taster(name, twitter));
					}
				}
				
				for (Taster taster : tasters) {
					if(taster.getNameTaster() != null) {
						System.out.println("Name:" + taster.getNameTaster() + " --- Twitter:" + taster.getTwitterhandlertaster());
						tr.save(taster);
					}
				}
			}
		};
	}
	
	@Bean
	CommandLineRunner CommandLineRunner(ProvinceRepository tr, CountryRepository cr) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Set<Province> provinces = new HashSet<>();
				try (Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME));
						CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
					for (CSVRecord csvRecord : csvParser) {
						String provinceName = "";
						String region1 = "";
						String region2 = "";
						String countryName = null;
						Country country = null;
						if (csvRecord.get(6) == null || csvRecord.get(6).trim().equals("")) {
							continue;
						} else {
							provinceName = csvRecord.get(6);
							region1 = csvRecord.get(7);
							region2 = csvRecord.get(8);
							countryName = csvRecord.get(1);
						}
						if(countryName != null) {
							country = cr.findById(countryName).get();
							provinces.add(new Province(provinceName, region1, region2, country));
						}
					}
				}
				
				for (Province province : provinces) {
					if(province.getProvinceName() != null) {
						System.out.println("Name:" + province.getProvinceName()
						+" -- Region:" + province.getRegion1()
						+" -- Region2:" + province.getRegion2()
						+" -- Country:" + province.getCountry().getName());
						tr.save(province);
					}
				}
			}
		};
	}
	
}
